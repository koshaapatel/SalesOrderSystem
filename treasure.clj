(ns test.s
    (:use [clojure.java.io]))
   (require '[clojure.string :as str])

 
   
(def global-var (atom nil))
(def Col (atom nil))
(def Row (atom nil))
(def T (atom nil))

(def up (atom nil))
(def down (atom nil))
(def right (atom nil))
(def left (atom nil))
(def up1 (atom nil))
(def down1 (atom nil))
(def right1 (atom nil))
(def left1 (atom nil))
(def flg1 (atom nil))


(def List (atom nil))
(def List1 (atom nil))
(def List2 (atom nil))
(def List3 (atom nil))

(def X (atom nil))
(def Y (atom nil))
(def X1 (atom nil))
(def Y1 (atom nil))

(def Col 0)
(def Row 0)
(def flg1 0)
(def T [])
(def List [])
(def List1 [])
(def List2 [])
(def List3 [])

(def up "u")
(def down "d")
(def right "r")
(def left "l")
(def up1 "u")
(def down1 "d")
(def right1 "r")
(def left1 "l")
(def X 7)
(def Y 11)
(def X1 0)
(def Y1 0)

(def global-var [])

 (try (
 (with-open [rdr (reader "map.txt")]
  (doseq [line (line-seq rdr)]
           (def Tmp2 ((comp #(map str %) seq) line))
      (def global-var (conj global-var Tmp2))
      
      (def Temp1 (count line))
      (def Col Temp1)
      (def temp2 (+ Row 1))
      (def Row  temp2)
      
             ))

 (def T  (vec (get-in global-var [0])))
 (def gj (nth T 0))
  (def List (conj List "0 0"))

(defn getVal
    [x1 y1]
    (if (and (< x1 Row) (> x1 -1))
    (do
        
    (if (and (< y1 Col) (> y1 -1))   
        (do
    (def T1  (vec (get-in global-var [x1])))
    (str (nth T1 y1)) 
        
        )))))




 (defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))  

 
 
 (def List1 (conj List1 (str "0" " " "0")))
  (def List3 (conj List3 (str "0" " " "0")))
   (def current1 (List3 0))
(while (or (not= up1 "@") (not= down1 "@") (not= right1 "@") (not= left1 "@") )
      (do       
          (def current1 (List1 (- (count List1) 1)))
        (if (.contains List1 current1)
            (do  
                )
            (do
                (def List1 (conj List1 current1))
            ))

          (def Current1 (str/split current1 #" "))
         (def ii1 (get Current1 0))
         (def iii1 (get Current1 1))
          (def X1 (parse-int ii1)) 
          (def Y1 (parse-int iii1))
          
          
          (def current (List 0))
         (def Current (str/split current #" "))
         (def ii (get Current 0))
         (def iii (get Current 1))
          (def X (parse-int ii)) 
          (def Y (parse-int iii))
          
          
        ;-------------------UP------------------
         (def t1u (- X 1)) 
         (def t2u Y)
          
          (def T  (vec (get-in global-var [t1u])))
          (if (not= T [])
                (do (def up (getVal t1u t2u))
                    )
                (do (def up nil) )
          )
          
          
              (def t1u1 (- X1 1)) 
         (def t2u1 Y1)
            (def T  (vec (get-in global-var [t1u1])))
          (if (not= T [])
                (do (def up1 (getVal t1u1 t2u1))
                    )
                (do (def up1 nil) )
          )
          
          ;-------------------Right------------------
          (def t1r X) 
          (def t2r (+ Y 1))
          
           (def T  (vec (get-in global-var [t1r])))
          (if (not= T [])
                (do (def right (getVal t1r t2r))
                    )
                (do (def right nil) )
          )
          
          (def t1r1 X1) 
          (def t2r1 (+ Y1 1))
         
          (def T  (vec (get-in global-var [t1r1])))
          (if (not= T [])
                (do (def right1 (getVal t1r1 t2r1))
                    )
                (do (def right1 nil) )
          )
          
          ;----------------left-------------
          
          (def t1l X) 
          (def t2l (- Y 1))
          
            (def T  (vec (get-in global-var [t1l])))
          (if (not= T [])
                (do (def left (getVal t1l t2l))
                    )
                (do (def left nil) ))
              
              
          
             (def t1l1 X1) 
          (def t2l1 (- Y1 1))
         
          (def T  (vec (get-in global-var [t1l1])))
          (if (not= T [])
                (do (def left1 (getVal t1l1 t2l1))
                    )
                (do (def left1 nil) )
          )
          ;--------------------down---------------
          
          (def t1d (+ X 1)) 
          (def t2d Y)
              (def T  (vec (get-in global-var [t1d])))
          (if (not= T [])
                (do (def down (getVal t1d t2d))
                    )
                (do (def down nil) )
          )
              
              
          
          (def t1d1 (+ X1 1)) 
          (def t2d1 Y1)
         
          (def T  (vec (get-in global-var [t1d1])))
          (if (not= T [])
                (do (def down1 (getVal t1d1 t2d1))
                    )
                (do (def down1 nil) )
          )
                    
          (if (or (= up "@") (= down "@") (= right "@") (= left "@"))
              (do
                  )
              (do
                  (if (and (not= up "#")  (not= up nil))
                      (do
                          (def List (conj List (str t1u " " t2u)))
                          
                          )
                      (do
                           )
                      )
                  (if (and (not= down "#")(not= down nil))
                      (do
                          (def List (conj List (str t1d " " t2d)))

                          )
                      (do
                          )
                      )
                  (if (and (not= right "#") (not= right nil))
                      (do
                          (def List (conj List (str t1r " " t2r)))
                          )
                      (do
                          )
                      )
                  (if (and (not= left "#") (not= left nil))
                      (do
                          (def List (conj List (str t1l " " t2l)))
                          )
                      (do
                          
                          )
                      )
                  )
              )

               (if (or (= up1 "@") (= down1 "@") (= right1 "@") (= left1 "@"))
              (do
              ;(println "Found!!!!!!!!")
                  (def flg1 1)
              (def List [])  
                  )
              (do
                  (def List3 [])
                  (if (and (not= up1 "#")  (not= up1 nil))
                      (do
                          
                          (if  (or (.contains List1 (str t1u1 " " t2u1)) (.contains List2 (str t1u1 " " t2u1)))
                            (do
                                        
                                )
                            (do
                                (def List3 (conj List3 (str t1u1 " " t2u1)))
                                
                                )
                              )

                          )
                      (do
                          
                          )
                      )
                  (if (and (not= down1 "#") (not= down1 nil))
                      (do
                          
                          (if  (or (.contains List1 (str t1d1 " " t2d1)) (.contains List2 (str t1d1 " " t2d1)))
                            (do

                                )
                            (do
                                (def List3 (conj List3 (str t1d1 " " t2d1)))
                                )
                              )

                          )
                      (do
                          )
                      )
                  (if (and (not= right1 "#")  (not= right1 nil))
                      (do
                          (if  (or (.contains List1 (str t1r1 " " t2r1)) (.contains List2 (str t1r1 " " t2r1)))
                            (do

                                )
                            (do
                                (def List3 (conj List3 (str t1r1 " " t2r1)))
                                )
                              )
                          
                          )
                      (do
                          )
                      )
                  (if (and (not= left1 "#")  (not= left1 nil))
                      (do
                          
                          (if  (or (.contains List1 (str t1l1 " " t2l1)) (.contains List2 (str t1l1 " " t2l1)))
                            (do

                                )
                            (do
                                (def List3 (conj List3 (str t1l1 " " t2l1)))
                                )
                              )
                          
                          
                          )
                      (do
                          
                          )
                      )
                  
                  
                  (if (not= List3 [])
                      (do
                            (def List1 (conj List1 (List3 0)))
                          )
                      (do
                          (def C (count List1))
                           (def List2 (conj List2 (List1 (- C 1))))
                          (def List1 (subvec  List1 0 (- C 1)))
                          
                          )
                        )

                     (def C (count List))
                  (def List (subvec  List 1 C))

                  )
              )
           
          (def T  (vec (get-in global-var [X])))
                           (def n "!")
                           (def T (assoc T Y n))
                           (def global-var (assoc-in global-var [X] T)) 
 
          ))

          )(catch Exception e
               (println)))

(def Col 0)
(def Row 0)
(def global-var2 [])
  (with-open [rdr (reader "map.txt")]

  (doseq [line (line-seq rdr)]
     
        (conj global-var2 line)
      ;(println ((comp #(map str %) seq) line))
      
      (def Tmp2 ((comp #(map str %) seq) line))
      (def global-var2 (conj global-var2 Tmp2))
      
      (def Temp1 (count line))
      (def Col Temp1)
      (def temp2 (+ Row 1))
      (def Row  temp2)
      
             ))
  
  
  
  (println "This is my challenge:" )
  (println)
  
   (loop [x 0]
      (when (< x Row)
   
           (loop [y 0]
      (when (< y Col)
          
          (def T  (vec (get-in global-var2 [x])))
          (print (get T y) "")
           
         (recur (+ y 1))))
          (println)
         (recur (+ x 1))))

 
 (def CC (count List1))
  
  (loop [y 0]
      (when (< y CC)
         
          
           (def Current1 (get List1 y))
        
          (def Current1 (str/split Current1 #" "))
           (def ii1 (get Current1 0))
         (def iii1 (get Current1 1))
             (def X1 (parse-int ii1)) 
          (def Y1 (parse-int iii1))
          (def T  (vec (get-in global-var [X1])))
   
  
     (def n "+")
    (def T (assoc T Y1 n))
   (def global-var (assoc-in global-var [X1] T)) 

         (recur (+ y 1))))
  
  (def CC (count List2))
  
  (loop [y 0]
      (when (< y CC)
         
          
           (def Current1 (get List2 y))
        
          (def Current1 (str/split Current1 #" "))
           (def ii1 (get Current1 0))
         (def iii1 (get Current1 1))
             (def X1 (parse-int ii1)) 
          (def Y1 (parse-int iii1))
          (def T  (vec (get-in global-var [X1])))
   
  
     (def n "!")
    (def T (assoc T Y1 n))
   (def global-var (assoc-in global-var [X1] T)) 
          
   
 (recur (+ y 1))))
  
       (println)
  
  (if (= flg1 0)
     (do
         (println "Uh oh, I could not find the treasure :-(")
         (println)
         ))
  (if (= flg1 1)
     (do
         (println "Woo hoo, I found the treasure :-)")
         (println)
         ))
    
   (loop [x 0]
      (when (< x Row)
   
           (loop [y 0]
      (when (< y Col)
 
          (def T  (vec (get-in global-var [x])))
   
          (print (get T y) "")
 
         (recur (+ y 1))))
          (println)
         (recur (+ x 1))))