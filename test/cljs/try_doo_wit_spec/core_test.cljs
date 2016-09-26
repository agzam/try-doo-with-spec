(ns try-doo-with-spec.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec :as s]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [cljs.spec.impl.gen :as gen]
            [try-doo-with-spec.core :refer :all]))

;; (s/def ::foo string?)

;; (defspec foo-test 100
;;   (prop/for-all [st (s/gen ::foo)]
;;      (< 0 (count st))))


