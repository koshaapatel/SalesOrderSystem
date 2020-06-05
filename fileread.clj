(require '[clojure.string :as str])

(def custlist [])
(def custvector {})
(def templist [])

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn splitcustdata
    ([data]
    (if (empty? data)
        (do custvector)
        (do 
            (println (first data))
            (def custlist (str/split (first data) #"\|"))
            (def custvector (conj custvector {(parse-int (get custlist 0)) [(get custlist 1) (get custlist 2) (get custlist 3)]} ))
            ; (def custvector (conj custvector (assoc custvector (parse-int (get custlist 0)) [(get custlist 1) (get custlist 2) (get custlist 3)])))          
            (recur (rest data))
        )
    ))

    ([]
    (do (into (sorted-map) custvector)))    
) 

; (defn getmap
;     []
;     (splitcustdata (str/split (slurp "cust.txt") #"\n"))
; )

(println (get custvector 2))

(def xx (get custvector 1))
(println (get xx 0))


(println "SORTED MAP")
(println (into (sorted-map) (splitcustdata (str/split (slurp "cust.txt") #"\n"))))

;(println "result:" (splitcustdata (str/split (slurp "cust.txt") #"\n")))
(println "MAPp")
(println (splitcustdata))


; (def custvector (conj custvector (assoc custvector :(parse-int (get custlist 0)) [(get custlist 1) (get custlist 2) (get custlist 3)])))

; (println (get custlist 0)) (sep (str/split (str/join (first data)) #"|"))