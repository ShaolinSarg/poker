(ns poker.cards)

(defrecord Card [value suit])

(def suits '(:hearts :clubs :spades :diamonds))
(def values '(:2 :3 :4 :5 :6 :7 :8 :9 :10 :J :Q :K :A))
(def new-deck (for [v values s suits] (Card. v s)))

(def straights (->> (conj values :A)
                    (partition 5 1)
                    (map #(into #{} %))))


(defn create-deck [] 
  "creates a new shuffled 52 card deck"
  (shuffle new-deck))

(defn deal-hand [deck]
  "returns a hand of 5 cards and the remaing deck after taking the 5 cards"
  ((juxt take drop) 5 deck))

(defn highest-card [card1 card2]
  (let [c1 (:value card1) c2 (:value card2)
        t1 (->> values
                (take-while #(not= % c1))
                (count))
        t2 (->> values
                (take-while #(not= % c2))
                (count))]
    (if (> t1 t2) card1 card2)))

(defn high-card [hand]
  (reduce highest-card hand))


;; (defn n-of-a-kind
;;   [hand]
;;   (->> hand
;;    (group-by :value)
;;        (vals)
;;        (filter #(> (count %) 1))
;;        (map #(let [hand (name-hand (count %))
;;                    high-card (:value (first %))] 
;;                {:hand hand :high-card high-card :cards %}))))


(defn flush? [hand]
  (every? #(= (:suit %) (:suit (first hand))) hand))


(defn straight? [hand]
  (if (some #{(->> hand
                   (map :value)
                   (into #{}))} straights) true false))


(defn straight-flush? [hand]
  (and (flush? hand) (straight? hand)))
