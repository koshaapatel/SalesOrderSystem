(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn printmenu
  []
    (println "*** Sales Menu ***")
    (println "------------------")
    (println "1. Display Customer Table")
    (println "2. Display Product Table")
    (println "3. Display Sales Table")
    (println "4. Total Sales for Customer")
    (println "5. Total Count for Product")
    (println "6. Exit")
    (println "Enter an option?")
)

(defn takeinput 
  []
      (printmenu)
      (let [readinput (parse-int (read-line))]
            (cond 
                 (= readinput 1) (do (println "i1") (recur) )
                 (= readinput 2) (do (println "i2") (recur) )
                 (= readinput 3) (do (println "i3") (recur) )
                 (= readinput 4) (do (println "i4") (recur) )
                 (= readinput 5) (do (println "i5") (recur) )
                 (= readinput 6) (do (println "Good Bye") )
                 (>= readinput 7) (do (println "Invalid option entered. Enter valid option.") (recur) )
                 (<= readinput 0) (do (println "Invalid option entered. Enter valid option.") (recur) )
            )
      )
)

(takeinput)