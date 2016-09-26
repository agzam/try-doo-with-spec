(ns try-doo-with-spec.server
  (:require 
   [hiccup.core :refer [html]]
   [ring.middleware.content-type :refer [wrap-content-type]]
   [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
   [ring.middleware.format :as fmt]
   [ring.middleware.not-modified :refer [wrap-not-modified]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.middleware.resource :refer [wrap-resource]]
   [ring.util.http-response :refer [ok header resource-response] :as resp]
   [prone.middleware :refer [wrap-exceptions]]
   [taoensso.timbre :as log] [bidi.ring :refer [make-handler]]))

(defn log-request [handler]
  (fn [request]
      (log/info request)
    (handler request)))

(defn index-page []
  (html
    [:html
     [:body
      [:h1 "Rock 'n' roll!"]]]))

(defn serve-index [_]
  (-> (index-page) ok (resp/content-type "text/html; charset=UTF-8")))

(def routes
  "This data structure represents bidi-style routes.
   Handlers are either a ring handler function, or a keyword."
  ["/"
   [["" :index]]])

(defn spa-handler
  "The argument handler comes from the routes above.
   This will either be a keyword indicating which page to display,
   or some handler function. The output is a ring handler.
   Since this is a single page app, all pages are served via serve-index."
  [handler]
  (if (keyword? handler) serve-index handler))

(def app
  (let [handler (-> routes
                  (make-handler spa-handler)
                  (wrap-resource "public")
                  (wrap-content-type)
                  (wrap-not-modified)
                  (wrap-defaults api-defaults)
                  log-request)]
    (-> handler wrap-exceptions wrap-reload)))
