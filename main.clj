
;
;(let [readinput (read-line)]
;
;     (if (= readinput "1" | = readinput "2")
;       (println "i1")
;       (println "Wrong")))


(println (let [alpha 7] (* alpha 5)))

(keyword 'by-nines)
(keyword 'from)
(keyword 'to)
(defn capture
      [by-nines from x to y]
      ( filter (fn [x] (zero? (mod x 9))) (range x y))
      )

(println (capture :by-nines :from 100 :to 150))
