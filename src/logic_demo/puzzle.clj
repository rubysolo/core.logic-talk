;; vim: set foldmethod=marker:
(ns logic-demo.puzzle
    (:refer-clojure :exclude [==])
    (:require [clojure.tools.macro :as macro])
    (:use clojure.core.logic
         [clojure.pprint :only [pprint]]
         [clojure.string :only [capitalize]]))





















(defn righto [a b s]
  (conde
    ;; case one: if a is first element of s, b is member of rest of s
    ((fresh [r]
           (firsto s a)
           (resto s r)
           (membero b r)))
    ;; case two: b is somewhere to the right of a in the rest of s
    ((fresh [r]
           (resto s r)
           (righto a b r)))))

(defn nextro [a b s]
  (conde
    ;; case one: if a is the first element in s, b must be the first
    ;; element in the rest of s
    ((fresh [r]
      (firsto s a)
      (resto s r)
      (firsto r b)))
    ;; case two: b is to the right of a in the rest of s
    ((fresh [r]
      (resto s r)
      (nextro a b r)))))

(defn nexto [a b s]
  (conde
    ((nextro a b s))
    ((nextro b a s))))

(defn lasto [a b]
  (fresh [x]
    (appendo x [a] b)))

















(defn coders
  []
  (macro/symbol-macrolet [_ (lvar)]
    (run 1 [houses]












          ;; start-demo
          ;; there are seven houses in which live programmers
          ;; given the clues, find the programming language,
          ;; drink, car, and pet of each programmer...
          ;; {{{
          (== houses [_ _ _ _ _ _ _])
          ;; }}}















          ;; we will represent each house as a 4-element vector:
          ;; [
          ;;   language
          ;;   drink
          ;;   car
          ;;   pet
          ;; ]
          ;;
          ;; example:  [:r, :c, :j, :l]














          ;; the C programmer drives a Land Rover
          ;; {{{
          (membero [:c _ :l _] houses)
          ;; }}}














          ;; the schnauzer barks at the iguana
          ;; through a hole in the fence
          ;; {{{
          (nexto [_ _ _ :i] [_ _ _ :s] houses)
          ;; ---------------------
          ;; (defn nexto [a b s]
          ;;   (conde
          ;;     ((nextro a b s))
          ;;     ((nextro b a s))))
          ;; ---------------------
          ;; (defn nextro [a b s]
          ;;   (conde
          ;;     ;; case one: if a is the first element in s,
          ;;     ;;           b must be the first element
          ;;     ;;           in the rest of s
          ;;     ((fresh [r]
          ;;       (firsto s a)
          ;;       (resto s r)
          ;;       (firsto r b)))
          ;;     ;; case two: b is to the right of a
          ;;     ;;           in the rest of s
          ;;     ((fresh [r]
          ;;       (resto s r)
          ;;       (nextro a b r)))))
          ;; ---------------------
          ;; }}}












          ;; driver of the Golf lives in the middle house
          ;; {{{
          (== houses [_ _ _ [_ _ :g _]_ _ _])
          ;; }}}














          ;; Ovaltine drinker has no car or pet
          ;; {{{
          (membero [_ :o :_ :_] houses)
          ;; }}}














          ;; Ruby programmer has no car,
          ;; rides pet unicorn to work
          ;; {{{
          (membero [:r _ :_ :u] houses)
          ;; }}}














          ;; Oldsmobile driver gave neighbor a gift
          ;; of coffee, which is enjoyed each morning
          ;; {{{
          (nexto [_ :c _ _] [_ _ :o _] houses)
          ;; }}}














          ;; Camaro driver is glad neighbor has no car
          ;; so that his cool car gets more visibility
          ;; {{{
          (nexto [_ _ :_ _] [_ _ :c _] houses)
          ;; }}}














          ;; Unix scripter lives to left of Ruby programmer
          ;; {{{
          (nextro [:u _ _ _] [:r _ _ _] houses)
          ;; }}}














          ;; Eiffel programmer drinks Evian
          ;; and lives in the last house
          ;; {{{
          (lasto [:e :e _ _] houses)
          ;; (defn lasto [a b]
          ;;   (fresh [x]
          ;;     (appendo x [a] b)))
          ;; }}}














          ;; Lua programmer lives two houses down
          ;; from Java programmer
          ;; {{{
          (fresh [b]
                 (membero b houses)
                 (nexto [:l _ _ _] b houses)
                 (nexto [:j _ _ _] b houses))
          ;; }}}















          ;; Impala driver drinks rum
          ;; {{{
          (membero [_ :r :i _] houses)
          ;; }}}














          ;; the two people without pets are neighbors
          ;; {{{
          (nexto [_ _ _ :_] [_ _ _ :_] houses)
          ;; }}}














          ;; ferret lives next to a petless house on one side,
          ;; and two doors down from the newt on the other
          ;; {{{
          (nexto [_ _ _ :_] [_ _ _ :f] houses)
          (fresh [b]
                 (membero b houses)
                 (nexto [_ _ _ :f] b houses)
                 (nexto [_ _ _ :n] b houses))
          ;; }}}














          ;; coffee drinker lives as far as possible
          ;; from Camaro driver, since he's such a jerk
          ;; {{{
          (conde
            ((firsto houses [_ :c _ _]) (lasto houses [_ _ :c _]))
            ((lasto houses [_ :c _ _]) (firsto houses [_ _ :c _])))
          ;; }}}














          ;; one of the carless coders has no favorite drink
          ;; {{{
          (membero [_ :_ :_ _] houses)
          ;; }}}














          ;; the OCaml coder lives next to the schnauzer owner
          ;; {{{
          (nexto [:o _ _ _] [_ _ _ :s] houses)
          ;; }}}














          ;; Lua coder lives next to the C coder
          ;; {{{
          (nexto [:l _ _ _] [:c _ _ _] houses)
          ;; }}}














          ;; the Impala driver lives somewhere to the right
          ;; of the Oldsmobile driver
          ;; {{{
          (righto [_ _ :o _] [_ _ :i _] houses)
          ;; ---------------------
          ;; (defn righto [a b s]
          ;;   (conde
          ;;     ;; case one: if a is first element of s,
          ;;     ;;           b is member of rest of s
          ;;     ((fresh [r]
          ;;            (firsto s a)
          ;;            (resto s r)
          ;;            (membero b r)))
          ;;     ;; case two: b is somewhere to the right
          ;;     ;;           of a in the rest of s
          ;;     ((fresh [r]
          ;;            (resto s r)
          ;;            (righto a b r)))))
          ;; ---------------------
          ;; }}}















         )))















(defn display-results [results]
  (doseq [row (apply map list (first results))]
    (doseq [c row]
      (cond
        (instance? clojure.lang.Symbol c) (print ".")
        :else (print (capitalize (name c)))))
    (print "\n")))
















;;(coders)
;;(display-results (coders))
