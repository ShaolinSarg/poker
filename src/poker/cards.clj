(ns poker.cards)

(def suits '(:hearts :clubs :spades :diamonds))

(def values '(:2 :3 :4 :5 :6 :7 :8 :9 :10 :J :Q :K :A))

(defrecord Card [value suit])

(def deck (for [v values s suits] (Card. v s)))

(defn highCard [card1 card2]
  (let [c1 (:value card1) c2 (:value card2)
          t1 (count (take-while #(not= % c1) values))
          t2 (count (take-while #(not= % c2) values))]
      (if (> t1 t2) card1 card2)))

(defn flush? [hand]
  (every? #(= (:suit %) (:suit (first hand))) hand))

