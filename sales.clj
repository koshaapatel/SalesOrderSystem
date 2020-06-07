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
        (do ) 
        (do 
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
        (do ) 
        (do 
            (def prodlist (str/split (first data) #"\|"))
            (def prodid (parse-int (get prodlist 0)) )
            (def unitcost (parse-float (get prodlist 2)) )
            (def prodvector (conj prodvector {prodid [(get prodlist 1) unitcost ]} ))
            (recur (rest data))
        )
    ))

)

(defn displayproddata
    []
    (def keyss (listtovector (keys (splitproddata) )) )
    (println "ProdID    ItemDescription    UnitCost") 
    (map (fn [arg] 
        (def proddata (get prodvector arg))
        (println arg"      " (get proddata 0)"           "(get proddata 1)   )
    )  
    keyss) 

)

(defn splitsalesdata
    ([]
    (do (into (sorted-map) salesvector) ))   

    ([data]
    (if (empty? data)
        (do ) 
        (do 
            (def saleslist (str/split (first data) #"\|"))
            (def salesid (parse-int (get saleslist 0)) )
            (def itemcount (parse-int (get saleslist 3)) )
            (def tempcustname (get custvector (parse-int (get saleslist 1)))) 
            (def tempitemname (get prodvector (parse-int (get saleslist 2)))) 
            (def salesvector (conj salesvector {salesid [(get tempcustname 0) (get tempitemname 0) itemcount ]} ))
            (recur (rest data))
        )
    ))

)

(defn displaysalesdata
    []
    (def keyss (listtovector (keys (splitsalesdata) )) )
    (println "SalesID   Name      ProductName    ItemCount") 
    (map (fn [arg] 
        (def salesdata (get salesvector arg))
        (println arg"     " (get salesdata 0)"    "(get salesdata 1)"     "(get salesdata 2)   )
    )  
    keyss) 

)

(defn splitsalescalcdata
    ([]
    (do (into (sorted-map) salescalcvector) ))   

    ([data]
    (if (empty? data)
        (do ) 
        (do 
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
    (println name": $"filteredprodid)

)

(defn logicfive 
    [item]
    (def productkeys  (listtovector (keys (splitproddata))   ) )

    (def prodidbyitem 
      (filter (fn [x] 
    
      (def proddata (get prodvector x)) 
      (def xitem (get proddata 0) )
      (if (= xitem item)
        x
      )) 
      productkeys) )

    (def filteredprodid (listtovector prodidbyitem)) 
    (def salescalckeys  (listtovector (keys (splitsalescalcdata))   ) )
    
    (def getsalesidbyprodid 
        (filter (fn [x] 
            (def salescalcdata (get salescalcvector x)) 
            
            (def prodid (get salescalcdata 1) )
            
            (if (= prodid (get filteredprodid 0))
              (do prodid)
              (do )
            )) salescalckeys  
            ) )  

    (def filteredsalesid (listtovector getsalesidbyprodid ))

    (defn itemcountbyprodid 
        [salesid]
        
        (map (fn [arg]         
        (def salesdata (get salescalcvector arg))     
        (def itemcount (get salesdata 2))
        itemcount

        )salesid
    
        )
    )

    (def totalitems (reduce + (listtovector (itemcountbyprodid filteredsalesid) ) )  ) 
    (println item": " totalitems)

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
    (println "\n********* Sales Menu **********")
    (println "-------------------------------")
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
                 (= readinput 1) (do (splitcustdata) (println "********************* CUSTOMER TABLE *********************")  (listtovector (remove nil?(displaycustdata) ))    (recur) ) 
                 (= readinput 2) (do (splitproddata) (println "*********** PRODUCT TABLE ***********") (listtovector (remove nil?(displayproddata) )) (recur) )
                 (= readinput 3) (do (splitsalesdata) (println "***************** SALES TABLE ****************") (listtovector (remove nil?(displaysalesdata) )) (recur) )
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