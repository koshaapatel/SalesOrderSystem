(use 'clojure.java.io)


(with-open [rdr (reader "cust.txt")]
  (doseq [line (line-seq rdr)]
    (println line)))

; (println (slurp "cust.txt"))


