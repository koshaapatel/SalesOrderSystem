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

(defn splitcustdata
    ([]
    (do (into (sorted-map) custvector) ))   

    ([data]
    (if (empty? data)
        (do ) ;(do (into (sorted-map) custvector) )
        (do ; (println (first data))
            (def custlist (str/split (first data) #"\|"))
            (def custid (parse-int (get custlist 0))) ; (if (= custid 1)  (println "@@@@@@"))
            ; (def custname (get custlist 1))
            (def custvector (conj custvector {custid [ (get custlist 1) (get custlist 2) (get custlist 3) ] } ))
            ; (def custvector (conj custvector {custid (set [ (get custlist 1) (get custlist 2) (get custlist 3) ]) } ))
            ; (def custvector (conj custvector (assoc custvector (parse-int (get custlist 0)) [(get custlist 1) (get custlist 2) (get custlist 3)]))) ; alternate of above line, we wont use though       
            (recur (rest data))
        )
    ))

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

;(println "SORTED CUST MAP")
(splitcustdata (str/split (slurp "cust.txt") #"\n")) 
(println "SORTED CUST MAP")
(println (splitcustdata))

; additional operations on custvector
; (println (get custvector 2))
; (def xx (get custvector 1))
; (println (get xx 0))
; (println (get custlist 0)) (sep (str/split (str/join (first data)) #"|")) ; this might be invalid

;(println "SORTED PROD MAP")
(splitproddata (str/split (slurp "prod.txt") #"\n")) 
(println "SORTED PROD MAP")
(println (splitproddata))

; (println "SORTED SALES MAP")
(splitsalesdata (str/split (slurp "sales.txt") #"\n")) 
(println "SORTED SALES MAP")
(println (splitsalesdata))

; println "SORTED SALES CALC MAP")
(splitsalescalcdata (str/split (slurp "sales.txt") #"\n")) 
(println "SORTED SALES CALC MAP")
(println (splitsalescalcdata))

(defn listtovector 
    [data]
   (vec data)
)

(println "Enter name:")
(def cname (read-line))

; (def saleskeys (vec (keys (splitsalesdata) )))
(def saleskeys  (listtovector (keys (splitsalesdata))   ) )
; println saleskeys)

; (def filtered (filter (fn [x] (< x 2)) [-1 4 0 7 2]))

(def salesidbyname 
    (filter (fn [x] 
    
    (def id (get salesvector x)) ; salesvector's list: [sue jones shoes 3]
    (def xname (get id 0) )
    (if (= xname cname)
        x
    )) 
    saleskeys) ) 

(def filteredsalesid (listtovector salesidbyname))

; (println "filtered salesid"filteredsalesid)

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

(println "total: "filteredprodid)



; (defn unitcostbyprodid
;     [data]
;     (map (fn [arg] 
    
;     (def proddata (get prodvector arg) )
;     (def cost (get proddata 1)   )
;     cost
 
;     )  
;     data)

; )

; (def filteredunitcost (listtovector (unitcostbyprodid filteredprodid) )  )

; (println "filtered unitcost"filteredunitcost)

; (def pid prodidbysalesid)

; (println pid)

; (println (keys (splitcustdata)))
; (println (map key (splitcustdata)))


; (myloop (keys (splitcustdata)))

; (defn myloop
;     (keys)
;     (println keys)
; )

; (def mm (splitcustdata))
; (def xx (get custvector 2))
; (println (get xx 0))