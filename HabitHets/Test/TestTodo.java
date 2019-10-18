import main.model.TodoOrganizer;
import org.junit.Assert;
import org.junit.Test;

public class TestTodo {

    /**
     * This test checks that the add-method works the way it is suppose to do.
     */

    @Test
    public void testTodoAdd(){
        TodoOrganizer todoOrganizer = TodoOrganizer.getInstant();
        todoOrganizer.add();
        todoOrganizer.add();
        Assert.assertEquals(2, todoOrganizer.getTodoList().size());
        Assert.assertEquals("testTodo", todoOrganizer.getTodoList().get(0).getTitle() );
        Assert.assertEquals(0, todoOrganizer.getTodoList().get(0).getId());
        Assert.assertEquals(1, todoOrganizer.getTodoList().get(1).getId());

    }


    /**
     * This test checks that you can change the title of the todoo.
     */

    @Test
    public void testTodoUpdateSubject(){
        TodoOrganizer todoOrganizer = TodoOrganizer.getInstant();
        todoOrganizer.add();
        todoOrganizer.add();
        Assert.assertEquals("testTodo", todoOrganizer.getTodoList().get(0).getTitle() );
        todoOrganizer.getTodoList().get(0).setTitle("Städa");
        Assert.assertEquals("Städa", todoOrganizer.getTodoList().get(0).getTitle() );
        Assert.assertEquals("testTodo", todoOrganizer.getTodoList().get(1).getTitle() );
    }

    /**
     * This test checks that when you complete a todoo that the todoo go from the todolist to donetodolist.
     */

    @Test
    public void testTodoRemove() {
        TodoOrganizer todoOrganizer = TodoOrganizer.getInstant();
        todoOrganizer.add();
        todoOrganizer.add();
        Assert.assertEquals(2, todoOrganizer.getTodoList().size());
        todoOrganizer.getTodoList().get(0).setTitle("Städa");
        Assert.assertEquals("Städa", todoOrganizer.getTodoList().get(0).getTitle() );
        todoOrganizer.doneTodoRemove(0);
        Assert.assertEquals(1, todoOrganizer.getTodoList().size());
        Assert.assertEquals(1, todoOrganizer.getDoneTodoList().size());
        Assert.assertEquals("testTodo", todoOrganizer.getTodoList().get(0).getTitle() );
        Assert.assertEquals("Städa", todoOrganizer.getDoneTodoList().get(0).getTitle() );
    }

    /**
     * This test checks that the length of the donetodolist never goes over the limit.
     */

    @Test
    public void testLengthOfDoneTodoList() {
        TodoOrganizer todoOrganizer = TodoOrganizer.getInstant();
        for (int a =0; a<8 ;a++){
            todoOrganizer.add();
            todoOrganizer.getTodoList().get(a).setTitle(""+a+"");
        }
        for (int r=0; r<7; r++){
           todoOrganizer.doneTodoRemove(todoOrganizer.getTodoList().size()-1);
        }

        Assert.assertEquals(1, todoOrganizer.getTodoList().size());
        Assert.assertEquals(5, todoOrganizer.getDoneTodoList().size());
        for(int i = 0; i< todoOrganizer.getDoneTodoList().size(); i++){

            System.out.println(todoOrganizer.getDoneTodoList().get(i).getTitle());
        }
    }

    /**
     * This test checks that you remove a todoo without complete it.
     */


    @Test
    public void deleteTodo(){
        TodoOrganizer todoOrganizer = TodoOrganizer.getInstant();
        todoOrganizer.add();
        todoOrganizer.add();
        Assert.assertEquals(2, todoOrganizer.getTodoList().size());
        todoOrganizer.remove(todoOrganizer.getTodoList().get(0).getId());
        Assert.assertEquals(1, todoOrganizer.getTodoList().size());
    }



}
