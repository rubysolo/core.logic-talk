;; MEMBERO
;; is this needle in the haystack?

  (membero needle haystack)















(run* [q] (membero q [1 2 3]))
;; => (1 2 3)
















(run* [q]
      (fresh [x]
        (== x 5)
        (membero q [1 2 x])))
;; => (1 2 5)










