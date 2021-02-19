package br.com.totvs.tcb.cobranca.fixture;

import com.github.javafaker.Faker;

import java.util.Locale;

public abstract class FixtureAbstract {

    public static final Faker faker = Faker.instance(Locale.ENGLISH);

}
