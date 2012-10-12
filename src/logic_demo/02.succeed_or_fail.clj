;; SUCCEED / FAIL
;; hardcoded goals

   (run 1 [q]
        ...goal...
        ...goal...
        succeed
        ...goal...)












(run 1 [q] succeed)
;; => (_.0)















(run 1 [q] fail)
;; => ()
















;; all goals must succeed (logical "AND")
(run 1 [q] succeed fail)
;; => ()












