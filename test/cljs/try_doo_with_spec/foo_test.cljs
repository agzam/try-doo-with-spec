(ns try-doo-with-spec.foo-test
  (:require
   [clojure.test.check.clojure-test :refer-macros [defspec]]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [clojure.test.check :as tc]
   [clojure.spec :as s]))

(defspec foo-str-test 50
 (prop/for-all [st (s/gen (s/and string?))]
   (= (count st))))
