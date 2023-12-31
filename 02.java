class Main {
    @Test public static void lotto_resource_returns_200_with_expected_id_and_winners() {
        when().
                get("/lotto/{id}", 5).
        then().
                statusCode(200).
                body("lotto.lottoId", equalTo(5),
                    "lotto.winners.winnerId", hasItems(23, 54));

    }

    public static void main(String[] args) {
        lotto_resource_returns_200_with_expected_id_and_winners();
    }
}