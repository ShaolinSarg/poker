(ns poker.core
  (:require [poker.cards :refer :all])
  (:gen-class))

(defn -main
  "Deals two hands of poker and gives the winner"
  [& args]
  (let
        [[hand-1 rest-deck] (poker.cards/deal-hand (poker.cards/create-deck))
         [hand-2 rest-deck2](poker.cards/deal-hand rest-deck )]
    (println (str "Player 1 has cards: " (pr-str (map #(str (:value %) (:suit %)) hand-1))))
    (println (str "Player 2 has cards: " (pr-str (map #(str (:value %) (:suit %)) hand-2))))
    (println (str "The winner is Player " (who-won hand-1 hand-2)))))


(defn who-won [hand1 hand2]
  "returns the winner"
  (let [p1-highest (high-card hand1)
        p2-highest (high-card hand2)]

    (if (= p1-highest (highest-card p1-highest p2-highest))
      "one"
      "two")))
