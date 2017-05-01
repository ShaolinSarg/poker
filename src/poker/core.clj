(ns poker.core
  (:require [poker.cards :refer :all])
  (:gen-class))

(defn -main
  "Deals two hands of poker and gives the winner"
  [& args]
  (let
        [[hand-1 rest-deck] (poker.cards/deal-hand (poker.cards/create-deck))
         [hand-2 rest-deck2](poker.cards/deal-hand rest-deck )]
    (do (println (str "Player 1 has cards: " (apply str hand-1)))
        (println (str "Player 2 has cards: " (apply str hand-2))))))


(defn name-hand
  [num-same]
  (cond
    (= 2 num-same) :pair
    (= 3 num-same) :3-of-a-kind
    :else :4-of-a-kind))

(defn n-of-a-kind
  [hand]
  (->> (group-by :value hand)
       (vals)
       (filter #(> (count %) 1))
       (map #(let [hand (name-hand (count %))
                   high-card (:value (first %))] 
               {:hand hand :high-card high-card :cards %}))))
