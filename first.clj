(defn func
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