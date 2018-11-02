package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import spark.ModelAndView;

/**
 * Helper class to extract data from Spark's {@link ModelAndView} objects
 * that are passed to the {@code TemplateEngine.render} method.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class TemplateEngineTester {

  /** Holds the BoardView-Model map from the Spark ModelAndView object. */
  private Object model;
  /** Holds the BoardView name from the Spark ModelAndView object. */
  private String viewName;

  /**
   * Make a Mockito {@link Answer} object to capture the {@link ModelAndView}
   * parameter to the template engine render method.
   *
   * @return  a Mockito {@link Answer} object
   */
  public Answer<Object> makeAnswer() {
    return new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final ModelAndView modelAndView = invocation.getArgument(0);
        TemplateEngineTester.this.model = modelAndView.getModel();
        TemplateEngineTester.this.viewName = modelAndView.getViewName();
        // the return value is not of interest
        return null;
      }
    };
  }

  /**
   * Assert that the BoardView-Model object exists.
   */
  public void assertViewModelExists() {
    assertNotNull(model, "the BoardView-Model exists");
  }

  /**
   * Assert that the BoardView-Model object exists.
   */
  public void assertViewModelIsaMap() {
    assertTrue(model instanceof Map, "the BoardView-Model is a Map");
  }

  /**
   * Assert that the BoardView-Model attributes matches the expected value.
   */
  public void assertViewModelAttribute(final String attrName, final Object expectedValue) {
    @SuppressWarnings("unchecked")
    final Map<String, Object> vm = (Map<String, Object>) model;
    assertEquals(expectedValue, vm.get(attrName));
  }

  /**
   * Assert that the BoardView-Model attribute is absent.
   */
  public void assertViewModelAttributeIsAbsent(final String attrName) {
    @SuppressWarnings("unchecked")
    final Map<String, Object> vm = (Map<String, Object>) model;
    assertFalse(vm.containsKey(attrName));
  }

  /**
   * Assert that the BoardView name exists and matches the expected value.
   */
  public void assertViewName(final String expectedName) {
    assertAll("BoardView assertions",
        () -> { assertNotNull(viewName, "the BoardView name exists"); },
        () -> { assertEquals(expectedName, viewName, "the BoardView name matches"); }
        );
  }
}
