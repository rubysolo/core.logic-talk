;; UNIFICATION
;; similar to "let" statement in clojure

  (== q 1)















(run* [q] (== q 1))
;; => (1)












(run* [q] (== 1 q))
;; => (1)












;- destructuring
(run* [q] (== [1 2 3] [1 q 3]))
;; => (2)














;; all goals must succeed together / logical "AND"
(run* [q] (== q 1) (== q 2))
;; => ()











