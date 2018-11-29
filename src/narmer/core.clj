(ns narmer.core
  (:use [org.httpkit.client :as http]
        [clojure.string :only [trim join]])
  (:gen-class))

(def engines ["https://torrentseeker.com/search.php?q="])

(defn handle-request [engine request]
  (println (str "Handling request " engine request))
  (let [{:keys [status headers body error] :as resp} @(http/get (str engine request))]
    (if error
      (println "Failed, exception: " error)
      (println "HTTP GET success: " status))))

(defn loop-engines [request]
  (for [e engines]
    (handle-request e request)))

(defn -main 
  [& params]
  (loop-engines (clojure.string/trim (clojure.string/join "+" params))))
