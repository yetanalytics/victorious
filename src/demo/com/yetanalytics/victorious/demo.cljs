(ns ^:figwheel-hooks com.yetanalytics.victorious.demo
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [com.yetanalytics.victorious :as v]))

(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))

(def pie-data (atom
               [{:x "Cats" :y 20} {:x "Dogs" :y 70}]))

(defn rand-pie! [_]
  (let [cats (rand-int 101)
        dogs (- 100 cats)]
    (reset! pie-data
           [{:x "Cats" :y cats} {:x "Dogs" :y dogs}])))

(defn pie []
  [:section.pie
   [:p.title "Pie"]
   [v/pie {:color-scale ["tomato", "orange", "gold", "cyan", "navy" ]
           :animate {:duration 2000
                     :easing "bounce"}
           :data @pie-data}]
   [:button {:on-click
             rand-pie!}
    "CHANGE PIE"]])

(defn page []
  [:div
   [pie]])

(defn mount [el]
  (reagent/render-component [page] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
