(require '[clojure.string :as str])

(def custlist [])
(def custvector {})
(def templist [])

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn seperate
    ([data]
    (if (empty? data)
        (do custvector)
        (do 
            (println (first data))
            (def templist (str/split (first data) #"\|"))
            (def custvector (conj custvector {(parse-int (get templist 0)) [(get templist 1) (get templist 2) (get templist 3)]} ))
            ; (def custvector (conj custvector (assoc custvector (parse-int (get templist 0)) [(get templist 1) (get templist 2) (get templist 3)])))          
            (recur (rest data))
        )
    ))

    ([]
    (do custvector))    
) 

; (defn getmap
;     []
;     (seperate (str/split (slurp "cust.txt") #"\n"))
; )

(println (get custvector 2))

(def xx (get custvector 1))
(println (get xx 0))


(println "SORTED MAP")
(println (into (sorted-map) (seperate (str/split (slurp "cust.txt") #"\n"))))

;(println "result:" (seperate (str/split (slurp "cust.txt") #"\n")))
(println "MAPp")
(println (seperate))


; (def custvector (conj custvector (assoc custvector :(parse-int (get templist 0)) [(get templist 1) (get templist 2) (get templist 3)])))

; (println (get templist 0)) (sep (str/split (str/join (first data)) #"|"))