(ns poker.cards-test
  (:require [clojure.test :refer :all]
            [poker.cards :refer :all]))


(deftest deck-tests
  (testing "a deck contains 52 unique cards"
    (is (= (count (create-deck)) 52))))

(deftest highest-card-tests
  (testing "highest-card"
    (testing "should return the highest of 2 cards"
      (let [c1 (->Card :3 :H)
            c2 (->Card :6 :D)]
        (is (= c2 (highest-card c1 c2)))))))

(deftest high-card-tests
  (testing "high-card"
    (testing "should return the highest card in a hand"
      (let [hand #{(->Card :3 :H)
                   (->Card :4 :D)
                   (->Card :A :D)
                   (->Card :K :D)
                   (->Card :2 :D)}]
        (is (= (->Card :A :D) (high-card hand)))))))

(deftest flush-tests
  (testing "flush?"
    (testing "should return true if all cards are the same suit"
      (is (true? (flush? #{(->Card :3 :H) (->Card :4 :H)}))))
    (testing "should return false if not all cards are the same suit"
      (is (false? (flush? #{(->Card :3 :H) (->Card :4 :D)}))))))

(deftest straight-tests
  (let [straight-hand #{(->Card :A :H)
                        (->Card :2 :D)
                        (->Card :3 :C)
                        (->Card :4 :S)
                        (->Card :5 :H)}
        not-straight-hand #{(->Card :A :H)
                        (->Card :2 :D)
                        (->Card :3 :C)
                        (->Card :4 :S)
                        (->Card :6 :H)}]
    
    (testing "straight?"
      (testing "should return true if the hand is a straight"
        (is (true? (straight? straight-hand))))
      (testing "should return false if the hand is not a straight"
        (is (false? (straight? not-straight-hand)))))))

(deftest straight-flush-tests
  (let [straight-flush-hand #{(->Card :A :H)
                        (->Card :2 :H)
                        (->Card :3 :H)
                        (->Card :4 :H)
                        (->Card :5 :H)}
        not-flush-hand #{(->Card :10 :H)
                        (->Card :J :D)
                        (->Card :Q :C)
                        (->Card :K :S)
                        (->Card :A :H)}
        not-straight-hand #{(->Card :A :H)
                        (->Card :Q :H)
                        (->Card :3 :H)
                        (->Card :4 :H)
                        (->Card :5 :H)}]
    (testing "straight-flush?"
      (testing "should return true for a straight flush"
        (is (true? (straight-flush? straight-flush-hand))))
      (testing "should return false for not a straight flush"
        (is (false? (straight-flush? not-flush-hand)))
        (is (false? (straight-flush? not-straight-hand)))))))
