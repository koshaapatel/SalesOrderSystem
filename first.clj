(defn func // gem
  []
      (println "wowo")
      (let [readinput (read-line)]
           (if (= readinput "1")
             (println "i1")
             (println "Wrong")))

      (println "func calling")
      (func)
      )

(func)




; (defn func  // trash
;   []
;       (println "wowo")
;       (let [readinput (read-line)]
;            (if (or (= readinput "1") (= readinput "2"))
;              (do (println "i1") (println "i2"))
;              (do (println "wrong") (if (or (= readinput "4") (= readinput "5"))
;              (do (println "i1") (println "i2"))
;              (do (println "wrong") )
;              ))
;              )

                 
;       )

;       (println "func calling")
;       (func)
;       )

; (func)




; (defn func // gem
;   []
;       (println "wowo")
;       (let [readinput (read-line)]
;            (if (= readinput "1")
;              (println "i1")
;              (println "Wrong")))

;       (println "func calling")
;       (func)
;       )

; (func)