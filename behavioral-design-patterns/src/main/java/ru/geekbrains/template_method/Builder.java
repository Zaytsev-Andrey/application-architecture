package ru.geekbrains.template_method;

abstract public class Builder {

    public void build() {
        test();
        lint();
        assemble();
        deploy();
    }

    abstract protected void test();

    abstract protected void lint();

    abstract protected void assemble();

    abstract protected void deploy();
}
