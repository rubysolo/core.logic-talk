;; APPENDO
;; does the right appended to the left
;; produce this result list?

  (appendo left right both)


















(run* [q] (appendo q [2 3 4] [1 2 3 4]))
;; => ((1))


















(run* [q] (appendo [1] [2 q 4] [1 2 3 4]))
;; => (3)


















(run* [q] (appendo [1] [2 q 5] [1 2 3 4]))
;; => ()

















