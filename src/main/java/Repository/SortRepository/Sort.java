package Repository.SortRepository;

import Model.Exceptions.FileException;
import jdk.internal.net.http.common.Pair;


import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Sort {
    public enum Direction {
        ASC, DESC
    }

    private ArrayList<Pair<Direction,String>> sortingMethods=new ArrayList<>();

    public Sort(String... fields) {
        Arrays.stream(fields)
                .forEach(field -> sortingMethods.add(new Pair(Direction.ASC,field)));
    }

    public Sort(Direction direction, String... fields) {
        Arrays.stream(fields)
                .forEach(field -> sortingMethods.add(new Pair(direction,field)));
    }

    public Sort and(Sort other) {
        sortingMethods.addAll(other.sortingMethods);
        return this;
    }

    public List<Object> sort(List<Object> entities) {
        AtomicReference<ArrayList<Object>> sorted = new AtomicReference<>(new ArrayList<>(entities));
        ArrayList<Pair<Direction,String>> reversed=new ArrayList(sortingMethods);
        Collections.reverse(reversed);
        reversed.stream()
                .forEach(pair -> {
                    Direction direction=pair.first;
                    String field=pair.second;
                    int sign;
                    if (direction.equals(Direction.DESC)) {
                        sign=-1;
                    }
                    else {
                        sign=1;
                    }
                    sorted.set((ArrayList<Object>) sorted.get().stream()
                            .sorted((entity1, entity2) -> {
                                try {
                                    Field field1 = entity1.getClass().getDeclaredField(field);
                                    field1.setAccessible(true);
                                    Field field2 = entity2.getClass().getDeclaredField(field);
                                    field2.setAccessible(true);
                                    Comparable parameter1 = (Comparable) field1.get(entity1);
                                    Comparable parameter2 = (Comparable) field2.get(entity2);
                                    return sign * parameter1.compareTo(parameter2);
                                } catch (IllegalAccessException e) {
                                } catch (NoSuchFieldException e) {
                                }
                                try {
                                    Field field1 = entity1.getClass().getSuperclass().getDeclaredField(field);
                                    field1.setAccessible(true);
                                    Comparable parameter1 = (Comparable) field1.get(entity1);
                                    Field field2 = entity2.getClass().getSuperclass().getDeclaredField(field);
                                    field2.setAccessible(true);
                                    Comparable parameter2 = (Comparable) field1.get(entity1);
                                    return sign * parameter1.compareTo(parameter2);
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    throw new FileException(field + " field is invalid!");
                                }
                            }).collect(Collectors.toList()));
                });
        return sorted.get();
    }
}

/*
    public List<Student> getAllStudents() {
        throw new RuntimeException("not yet implemented");
//        Sort sort = new Sort("name"); //sort asc by name

//        Sort sort = new Sort("group"); //sort asc by group

        //sort desc by group and desc by name (should work with any number of fields)
//        Sort sort = new Sort(Sort.Direction.DESC, "group", "name");

        //sort desc by group and asc by name
//        Sort sort = new Sort(Sort.Direction.DESC, "group")
//                .and(new Sort("name"));

//        return StreamSupport.stream(repository.findAll(sort).spliterator(), false)
//
 */