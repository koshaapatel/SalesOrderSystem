; (def db {:classname "com.mysql.jdbc.Driver" 
;          :subprotocol "mysql" 
;          :subname "//100.100.100.100:3306/clo" 
;          :username "usr" :password "pwd"})


; (doseq [keyval db] (prn keyval))


; (def foo #{ 1 2 3 :a} )

; (println (get foo :a))


(def person{})
(def person {:name "Steve" :age 24 :salary 7886 :company "Acme"})


(println person)
