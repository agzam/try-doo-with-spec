(ns try-doo-with-spec.foo-test
  (:require
   [cljs.test :refer-macros [deftest is]]
   [clojure.test.check.clojure-test :refer-macros [defspec]]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :refer-macros [for-all]]
   [clojure.test.check :as tc]
   [com.gfredericks.test.chuck.generators :as gen']
   [cljsjs.moment]
   [clojure.spec :as s]))


(defn dt-str [d] (.format (js/moment (js/Date. d))))

(def iso-date-regex #"^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}-\d{2}:\d{2}$")

(s/def ::date-iso (s/with-gen (s/and #(re-find iso-date-regex %) string?)
                    #(gen'/for [d (gen'/datetime)]
                       (dt-str d))))

(deftest gen-datetime-test
  (prn (gen/sample (gen'/datetime) 20)))

(deftest gen-date-iso-tes
  (prn (gen/sample (s/gen ::date-iso) 20)))
