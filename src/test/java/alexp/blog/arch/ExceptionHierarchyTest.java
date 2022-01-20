package alexp.blog.arch;

import alexp.blog.exception.LogicalError;
import alexp.blog.exception.SystemError;
import alexp.blog.exception.ValidationError;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.conditions.ArchConditions.implement;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "alexp.blog")
public class ExceptionHierarchyTest {
    private static final ArchCondition IMPLEMENT_BASE_EXCEPTION_CONDITION = implement(ValidationError.class).
            or(implement(SystemError.class).
                    or(implement(LogicalError.class)));

    @ArchTest
    public static final ArchRule exceptions_should_not_inherit_from_a_generic_exception = classes()
            .that().areAssignableTo(Exception.class).should(IMPLEMENT_BASE_EXCEPTION_CONDITION);


}
