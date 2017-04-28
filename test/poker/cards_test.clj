(ns poker.cards-test
  (:require [clojure.test :refer :all]
            [poker.cards :refer :all]))


(deftest deck-tests
  (testing "a deck contains 52 unique cards"
    (is (= (count deck) 52))))

(deftest high-card-tests
  (testing "high-card"
    (testing "should return the highest of 2 cards"
      (let [c1 (->Card :3 :H)
            c2 (->Card :6 :D)]
        (is (= c2 (highCard c1 c2)))))
    (testing "should return nil when the cards are equal"
      (let [c1 (->Card :3 :H)
            c2 (->Card :3 :D)]
        (is (= nil (highCard c1 c2)))))))

(deftest flush-tests
  (testing "flush?"
    (testing "should return true if all cards are the same suit"
      (is (true? (flush? #{(->Card :3 :H) (->Card :4 :H)}))))
    (testing "should return false if not all cards are the same suit"
      (is (false? (flush? #{(->Card :3 :H) (->Card :4 :D)}))))))
