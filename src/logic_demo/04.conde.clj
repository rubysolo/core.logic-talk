;; CONDE
;; parallel universes

  (conde
    (...goals...)
    (...other goals...)
    (...yet other goals...))














(run* [q]
      (conde (succeed)
             (fail)))
;; => (_.0)













(run* [q]
      (conde [(== [1 2 3] [1 q 3])]
             [(== [q 2 3] [1 2 3])]))
;; => (2 1)











