public class SpecificationFactory {
    private static final String UNKNOWN_FIELD_TYPE = "Unknown book field type.";

    public Specification create(BookFieldType field) throws UnknownFieldException {
        switch (field) {
            case CREATION_YEAR -> {
                return new CreationYearSpecification();
            }
            case AUTHOR -> {
                return new AuthorSpecification();
            }
            case TITLE -> {
                return new TitleSpecification();
            }
            default -> throw new UnknownFieldException(UNKNOWN_FIELD_TYPE);
        }
    }
}