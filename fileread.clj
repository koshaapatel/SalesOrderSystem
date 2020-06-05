(require '[clojure.string :as str])

(def custlist [])
(def custvector {})
(def prodlist [])
(def prodvector {})
(def saleslist [])
(def salesvector {})

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn parse-float [s]
    (Float/parseFloat s))

(defn splitcustdata
    ([]
    (do (into (sorted-map) custvector) ))   

    ([data]
    (if (empty? data)
        (do (into (sorted-map) custvector) )
        (do ; (println (first data))
            (def custlist (str/split (first data) #"\|"))
            (def custvector (conj custvector {(parse-int (get custlist 0)) [(get custlist 1) (get custlist 2) (get custlist 3)]} ))
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
        (do (into (sorted-map) prodvector) )
        (do ; (println (first data))
            (def prodlist (str/split (first data) #"\|"))
            (def prodvector (conj prodvector {(parse-int (get prodlist 0)) [(get prodlist 1) (parse-float (get prodlist 2))]} ))
            (recur (rest data))
        )
    ))

)

(defn splitsalesdata
    ([]
    (do (into (sorted-map) salesvector) ))   

    ([data]
    (if (empty? data)
        (do (into (sorted-map) salesvector) )
        (do ; (println (first data))
            (def saleslist (str/split (first data) #"\|"))
            (def tempcustname (get custvector (parse-int (get saleslist 1)))) ; (println (get tempcustname 0))
            (def tempitemname (get prodvector (parse-int (get saleslist 2)))) ; (println (get tempitemname 0))
            (def salesvector (conj salesvector {(parse-int (get saleslist 0)) [(get tempcustname 0) (get tempitemname 0) (parse-int (get saleslist 3)) ]} ))
            (recur (rest data))
        )
    ))

)

(println "SORTED CUST MAP")
(println (splitcustdata (str/split (slurp "cust.txt") #"\n")) )
(println "CUST MAP")
(println (splitcustdata))

; additional operations on custvector
; (println (get custvector 2))
; (def xx (get custvector 1))
; (println (get xx 0))
; (println (get custlist 0)) (sep (str/split (str/join (first data)) #"|")) ; this might be invalid

(println "SORTED PROD MAP")
(println (splitproddata (str/split (slurp "prod.txt") #"\n")) )
(println "PROD MAP")
(println (splitproddata))

(println "SORTED SALES MAP")
(println (splitsalesdata (str/split (slurp "sales.txt") #"\n")) )
(println "SALES MAP")
(println (splitsalesdata))