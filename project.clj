(defproject try-doo-with-spec "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clojure-future-spec "1.9.0-alpha12-2"]
                 [org.clojure/clojurescript "1.9.229"]
                 [com.taoensso/timbre "4.7.4"]
                 [org.clojure/test.check "0.9.0"]
                 [hiccup "1.0.5" :exclusions [cljsjs/react]]
                 [ring "1.6.0-beta6"]
                 [ring/ring-defaults "0.3.0-beta1"]
                 [ring-middleware-format  "0.7.0"]
                 [metosin/ring-http-response "0.8.0"]
                 [bidi "2.0.11"]
                 [prone "1.1.2"]
                 [com.gfredericks/test.chuck "0.2.7"]                
                 [com.cemerick/url "0.1.2-SNAPSHOT"]
                 [cljsjs/moment "2.10.6-4"]]

  :plugins [[lein-cljsbuild "1.1.4" :exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.7" :exclusions [[org.clojure/clojurescript]]]]

  :clean-targets ^{:protect false} ["resources/public/js/" "target" "out"]

  :profiles {:dev {:source-paths ["dev" "src/clj" "src/cljs" "src/cljc" "test/cljc" "test/clj"]
                   :dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.7"]]
                   :repl-options {:init-ns          user
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
                                  :port             7001}}
             :test {:source-paths ["src/clj" "test/clj"]}}

  :cljsbuild {:builds [{:id           "test"
                        :source-paths ["src/cljs" "test/cljs"]
                        :compiler     {:output-to     "resources/public/tests/test.js"
                                       :output-dir    "resources/public/tests/"
                                       :main          "try-doo-with-spec.runner"
                                       :verbose       true
                                       :source-map    true
                                       :optimizations :none}}]}
  :doo {:build "test"
        :paths {:phantom "/usr/local/bin/phantomjs"}
        :alias {:default  [:phantom]}})
