package me.learn.springnote.myTest;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MyTest {

    @Test
    void asList(){
        String str = "test";
        List<String> list = Arrays.asList(str);

        assertThat(list.get(0)).isEqualTo("test");

        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
                () -> list.add("test2")
        );

        String[] arr = {"C1", "C2"};
        List<String> list2 = Arrays.asList(arr);

        list2.set(0, "changed Test");
        assertThat(list2.get(0)).isEqualTo("changed Test");

        arr[1] = "changed Test2";
        assertThat(list2.get(1)).isEqualTo("changed Test2");
        System.out.println("list2.toString() = " + list2.toString());
    }

    @Test
    void singletonListTest(){
        String str = "test";
        List<String> list = Collections.singletonList(str);

        assertThat(list.get(0)).isEqualTo("test");
        System.out.println("list = " + list.toString());

        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
                () -> list.add("test2")
        );
    }

    @Test
    void memoryTest() throws IOException{
        System.out.println("Arrays.asList : " + getBytesFromList(Arrays.asList("test")));
        System.out.println("Collections.singletonList : " + getBytesFromList(Collections.singletonList("test")));
    }

    public long getBytesFromList(List list) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteStream);
        outputStream.writeObject(list);
        outputStream.close();
        return byteStream.toByteArray().length;
    }
}
