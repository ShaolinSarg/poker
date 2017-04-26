(defproject com.dsarg/poker "1.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot poker.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
