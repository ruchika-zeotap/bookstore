package repositories;


public interface Repository<E, T> {
    E fetchCompleteDataStore();

    E fetchPaginatedDataStore(T query, String token);
}
