(ns poker.core
  (:require [poker.cards :refer :all])
  (:gen-class))

(defn -main
  "Deals two hands of poker and gives the winner"
  [& args]
  (let
        [[hand-1 rest-deck] (poker.cards/deal-hand (poker.cards/create-deck))
         [hand-2 rest-deck2](poker.cards/deal-hand rest-deck )]
    (do (println (str "Player 1 has cards: " (pr-str (map #(str (:value %) (:suit %)) hand-1))))
        (println (str "Player 2 has cards: " (pr-str (map #(str (:value %) (:suit %)) hand-2)))))))


(defn name-hand
  [num-same]
  (cond
    (= 2 num-same) :pair
    (= 3 num-same) :3-of-a-kind
    :else :4-of-a-kind))

