(require '[clojure.string :as str])

(def custlist [])
(def custvector {})
(def templist[])

(defn sep
    [dataa]
    (if (empty? dataa)
        (println "nothing")
        (do (println (str/join dataa))
        (println (str/split (str/join dataa) #"|"))
        )
    ) 
)
(defn seperate
    [data]
    (if (empty? data)
        (println "nothing")
        (do (println (first data))
        (sep (str/split (str/join (first data)) #"|"))
        (recur (rest data)))
    )     
)
(println "result:" (seperate (str/split (slurp "cust.txt") #"\n")))

; (defn sep
;     [dataa]
;     (if (empty? dataa)
;         (println "nothing")
;         (do (println (str/join dataa))
;         (println (str/split (str/join (first dataa)) #"|"))
;         )
;     ) 
; )