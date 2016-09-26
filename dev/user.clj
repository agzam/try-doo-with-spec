(ns user
 (:require
  [figwheel-sidecar.repl-api :as ra]
  [taoensso.timbre :as log]))


(def figwheel-config
  {:figwheel-options {:server-port 3333
                      :css-dirs ["resources/public"]
                      :ring-handler 'try-doo-with-spec.server/app}
   :build-ids ["dev"]
   :all-builds
   [{:id "dev"
     :figwheel true
     :source-paths ["src"]
     :compiler {:main "try-doo-with-spec.app"
                :asset-path "/js/out"
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :parallel-build true
                :source-map-timestamp true
                :verbose true}}]})

;; Tools for running the backend/frontend

(defn start []
  (ra/start-figwheel! figwheel-config) nil)

(defn stop []
  (ra/stop-figwheel!)
  nil)

(defn reload []
  (stop)
  (start))

(defn repl [& profile]
  (ra/cljs-repl (or profile "dev")))

;; Makes sure to end the OS threads that have spawned.
(.addShutdownHook (Runtime/getRuntime) (Thread. stop))
