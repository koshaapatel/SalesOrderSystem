(def users [{:name "James" :age 26}  {:name "John" :age 43}])

;; update the age of the second (index 1) user 
(assoc-in users [1 :age] 44)
;;=> [{:name "James", :age 26} {:name "John", :age 44}]
(println (assoc-in users [1 :age] 44))

;; insert the password of the second (index 1) user
(assoc-in users [1 :password] "nhoJ")
;;=> [{:name "James", :age 26} {:password "nhoJ", :name "John", :age 43}]
(println (assoc-in users [1 :password] "nhoJ"))

;; create a third (index 2) user
;; Also (assoc m 2 {...}) or (conj m {...})
(assoc-in users [2] {:name "Jack" :age 19})  
;;=> [{:name "James", :age 26} {:name "John", :age 43} {:name "Jack", :age 19}]
(println (assoc-in users [2] {:name "Jack" :age 19})  )

;; From http://clojure-examples.appspot.com/clojure.core/assoc-in