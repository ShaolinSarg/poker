(ns poker.cards)

(defrecord Card [value suit])

(def suits '(:hearts :clubs :spades :diamonds))
(def values '(:2 :3 :4 :5 :6 :7 :8 :9 :10 :J :Q :K :A))
(def new-deck (for [v values s suits] (Card. v s)))

(def straights (->> (conj values :A)
                    (partition 5 1)
                    (map #(into #{} %))))


(defn create-deck [] 
  (shuffle new-deck))

(defn deal-hand [deck]
  ((juxt take drop) 5 deck))

(defn highCard [card1 card2]
  (let [c1 (:value card1) c2 (:value card2)
        t1 (->> values
                (take-while #(not= % c1))
                (count))
        t2 (->> values
                (take-while #(not= % c2))
                (count))]
    (cond
      (> t1 t2) card1
      (> t2 t1) card2
      :else nil)))

(defn flush? [hand]
  (every? #(= (:suit %) (:suit (first hand))) hand))

(defn straight? [hand]
  (if (some #{(->> hand
                   (map :value)
                   (into #{}))} straights) true false))
