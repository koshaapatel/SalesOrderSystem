(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn func
      []
      (println "wowo")
      (let [readinput (parse-int (read-line))]
           (if (= readinput 1)
                 (println "i1")
                 (println "Wrong")))

      (println "func calling")
      (func)
      )

(func)


(println (parse-int "10"))

(println (type (parse-int "10")))


(func)



;
;(let [readinput (read-line)]
;
;     (if (= readinput "1" | = readinput "2")
;       (println "i1")
;       (println "Wrong")))







; (defn func
;       []
;       (println "wowo")
;       (let [readinput (read-line)]
;            (if (= readinput "1")
;                  (println "i1")
;                  (println "Wrong")))

;       (println "func calling")
;       (func)
;       )

; (func)







; (println (let [alpha 7] (* alpha 5)))

; (keyword 'by-nines)
; (keyword 'from)
; (keyword 'to)
; (defn capture
;       [by-nines from x to y]
;       ( filter (fn [x] (zero? (mod x 9))) (range x y))
;       )

; (println (capture :by-nines :from 100 :to 150))