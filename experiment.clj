(require '[clojure.string :as str])
(println (str/split "Clojure|is|awesome!" #"\|"))

(println (re-seq #"[^|]+" "a|b|c|d|e"))

(println (into (sorted-map) [ [:a 1] [:c 3] [:b 2] ] ))

(def test-map {:account-no [1 2 3] :lname [4,5,6] :fnam [7 8 9]})
(println (assoc test-map :fnam [0 0 0]))

(println test-map)

(println (into (sorted-map) {:a 2 :b 1 :d "ok" :c "okok" }))

(def temp [])
(println (conj temp 1 2 3))

(println (conj [1 2 3] 4))

; (println (let [grade 85]
;          (cond
;            (>= grade 90) "A"
;            (>= grade 80) (do (println "baba") (println "Kba") "B")
;            (>= grade 70) "C"
;            (>= grade 60) "D"
;            :else "F"))
;  )


; (require '[clojure.string :as str])
;  (let [refined (str/split (slurp "cust.txt") #" ")]
;      (println refined)
;  )
