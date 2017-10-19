package pl.tomo.luxmed;

abstract class FilterData {

    private final String id;
    private final String name;

    public FilterData(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
