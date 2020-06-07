(require '[clojure.string :as str])

(def custlist [])
(def custvector {})
(def prodlist [])
(def prodvector {})
(def saleslist [])
(def salesvector {})
(def salescalclist [])
(def salescalcvector {})

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn parse-float [s]
    (Float/parseFloat s))

(defn listtovector 
    [data]
   (vec data))

(defn splitcustdata
    ([]
    (do (into (sorted-map) custvector) ))   

    ([data]
    (if (empty? data)
        (do ) ;(do (into (sorted-map) custvector) )
        (do ; (println (first data))
            (def custlist (str/split (first data) #"\|"))
            (def custid (parse-int (get custlist 0)))
            (def custvector (conj custvector {custid [ (get custlist 1) (get custlist 2) (get custlist 3) ] } ))
            (recur (rest data))
        )
    ))

)

(defn displaycustdata
    []
    (def keyss (listtovector (keys (splitcustdata) )) )
    (println "Custid    Name    Address     PhoneNumber") 
    (map (fn [arg] 
        (def salesdata (get custvector arg))
        (println arg"   " (get salesdata 0)"    "(get salesdata 1)"     "(get salesdata 2)   )
    )  
    keyss) 

) 


(defn splitproddata
    ([]
    (do (into (sorted-map) prodvector) ))   

    ([data]
    (if (empty? data)
        (do ) ; (do (into (sorted-map) prodvector) )
        (do ; (println (first data))
            (def prodlist (str/split (first data) #"\|"))
            (def prodid (parse-int (get prodlist 0)) )
            (def unitcost (parse-float (get prodlist 2)) )
            (def prodvector (conj prodvector {prodid [(get prodlist 1) unitcost ]} ))
            (recur (rest data))
        )
    ))

)

(defn splitsalesdata
    ([]
    (do (into (sorted-map) salesvector) ))   

    ([data]
    (if (empty? data)
        (do ) ; (do (into (sorted-map) salesvector) )
        (do ; (println (first data))
            (def saleslist (str/split (first data) #"\|"))
            (def salesid (parse-int (get saleslist 0)) )
            (def itemcount (parse-int (get saleslist 3)) )
            (def tempcustname (get custvector (parse-int (get saleslist 1)))) ; (println (get tempcustname 0))
            (def tempitemname (get prodvector (parse-int (get saleslist 2)))) ; (println (get tempitemname 0))
            (def salesvector (conj salesvector {salesid [(get tempcustname 0) (get tempitemname 0) itemcount ]} ))
            (recur (rest data))
        )
    ))

)

(defn splitsalescalcdata
    ([]
    (do (into (sorted-map) salescalcvector) ))   

    ([data]
    (if (empty? data)
        (do ) ; (do (into (sorted-map) salescalcvector) )
        (do ; (println (first data))
            (def salescalclist (str/split (first data) #"\|"))
            (def salesid (parse-int (get salescalclist 0)) )
            (def custid (parse-int (get salescalclist 1)) )
            (def prodid (parse-int (get salescalclist 2)) )
            (def itemcount (parse-int (get salescalclist 3)) )
            (def salescalcvector (conj salescalcvector {salesid [ custid prodid itemcount ]} ))
            (recur (rest data))
        )
    ))

)

(defn logicfour
    [name]
    (def saleskeys  (listtovector (keys (splitsalesdata))   ) )
   ; (println "SALESKEYS"saleskeys)

    (def salesidbyname 
      (filter (fn [x] 
    
      (def id (get salesvector x)) 
      (def xname (get id 0) )
      (if (= xname name)
        x
      )) 
      saleskeys) ) 

    (def filteredsalesid (listtovector salesidbyname))

    (defn prodidbysalesid 
        [data]
        (map (fn [arg] 
    
        (def salesdata (get salescalcvector arg))
        (def prodid (get salesdata 1))
    
        (def itemcount (get salesdata 2))
    
        (def co (get prodvector prodid))
        (def cost (get co 1))
        (def total (* itemcount cost))
        total
        )  
        data)
    ) 

    (def filteredprodid (reduce + (listtovector (prodidbysalesid filteredsalesid) ) )  )

    (println name": "filteredprodid)

)

(defn logicfive 
    [item]
    (println "item"item)
    (def productkeys  (listtovector (keys (splitproddata))   ) )

    (def prodidbyitem 
      (filter (fn [x] 
    
      (def proddata (get prodvector x)) 
      (def xitem (get proddata 0) )
      (if (= xitem item)
        x
      )) 
      productkeys) )

    (def filteredprodid (listtovector prodidbyitem)) ; for shoes: 1
    (println "filteredprodid"filteredprodid)

    (def salescalckeys  (listtovector (keys (splitsalescalcdata))   ) )
    ;(println "salescalckeys"salescalckeys)

    (def getsalesidbyprodid 
        (filter (fn [x] 
            (def salescalcdata (get salescalcvector x)) ; salescalcvector's list: [1 1 3] [2 2 3] [2 1 1] [3 3 4]
            ; (println "SALESCALCDATA"salescalcdata)
            (def prodid (get salescalcdata 1) )
            (println "SALESCALCPRODID"prodid)
            
            (if (= prodid (get filteredprodid 0))
              (do prodid)
              (do )
            )) salescalckeys  
            ) )  

    (def filteredsalesid (listtovector getsalesidbyprodid ))
    (println "filteredsalesid"filteredsalesid)


    (defn itemcountbyprodid 
        [salesid]
        ;(println "salesid"salesid)
        (map (fn [arg] 
        ;(println "arg"arg)
        (def salesdata (get salescalcvector arg))
        ;(println "salesdata"salesdata)
        (def itemcount (get salesdata 2))
        (println "itemcount"itemcount)
  
        itemcount

        )salesid
    
        )
    )

    (def totalitems (reduce + (listtovector (itemcountbyprodid filteredsalesid) ) )  ) ; filteredsalesid

    (println "totalitems" totalitems)

)

(defn loaddatabase
  []
  (splitcustdata (str/split (slurp "cust.txt") #"\n")) 
  (splitproddata (str/split (slurp "prod.txt") #"\n")) 
  (splitsalesdata (str/split (slurp "sales.txt") #"\n")) 
  (splitsalescalcdata (str/split (slurp "sales.txt") #"\n")) 
)

(defn printmenu
  []
    (println "*** Sales Menu ***")
    (println "------------------")
    (println "1. Display Customer Table")
    (println "2. Display Product Table")
    (println "3. Display Sales Table")
    (println "4. Total Sales for Customer")
    (println "5. Total Count for Product")
    (println "6. Exit")
    (println "Enter an option?")
)

(defn takeinput 
  []
      (printmenu)
      (let [readinput (parse-int (read-line))]
            (cond 
                 ; (= readinput 1) (do (splitcustdata) (println "CUSTOMER TABLE")   (def result (listtovector (remove nil?(displaycustdata) ))) (println result)    (recur) )
                 ; (= readinput 1) (do (splitcustdata) (println "CUSTOMER TABLE")   (def result (listtovector (remove nil?(displaycustdata) )))     (recur) )
                 (= readinput 1) (do (splitcustdata) (println "CUSTOMER TABLE")  (listtovector (remove nil?(displaycustdata) ))    (recur) )
                 (= readinput 2) (do (println "PRODUCT TABLE") (println (splitproddata)) (recur) )
                 (= readinput 3) (do (println "SALES TABLE") (println (splitsalesdata)) (recur) )
                 (= readinput 4) (do (println "Enter customer name:") (def cname (read-line)) (logicfour cname)  (recur) )
                 (= readinput 5) (do (println "Enter product name:") (def prodname (read-line)) (logicfive prodname) (recur) )
                 (= readinput 6) (do (println "Good Bye") )
                 (>= readinput 7) (do (println "Invalid option entered. Enter valid option.") (recur) )
                 (<= readinput 0) (do (println "Invalid option entered. Enter valid option.") (recur) )
            )
      )
)

(loaddatabase)
(takeinput)