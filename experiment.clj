(println (let [grade 85]
         (cond
           (>= grade 90) "A"
           (>= grade 80) (do (println "baba") (println "Kba") "B")
           (>= grade 70) "C"
           (>= grade 60) "D"
           :else "F"))
 )