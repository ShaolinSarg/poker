(ns poker.core-test
  (:require [clojure.test :refer :all]
            [poker.core :refer :all]))

(deftest winner-tests
  (testing "who-won should"
    (testing "return the player with the highest card"
      (let [p1-hand #{{:value :3 :suit :Clubs}}
            p2-hand #{{:value :A :suit :Clubs}}]
        (is (= "two" (who-won p1-hand p2-hand)))
        (is (= "one" (who-won p2-hand p1-hand)))))))
