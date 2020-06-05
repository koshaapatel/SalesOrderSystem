(def m {:1 {:value 0, :active false}, :2 {:value 0, :active false}})

(println (update-in m [:1] assoc :value 1 :active true))




(def ds [{:id 1.0 :name "name1"}
         {:id 2.0 :name "name2"}
         {:id 3.0 :name "name3"}])

(println 
(map (fn [x] (update-in x [:name] #(if (= "name2" %) % "not 2"))) ds))
