package ar.com.grupoesfera.repartir.steps.grupos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CompuestosPorAlMenosDosMiembrosSteps extends CucumberSteps {

    private List<String> miembros;

    private String mensaje;

    @Cuando("el usuario crea un grupo indicando que sus miembros son {miembros}")
    public void elUsuarioCreaUnGrupoIndicandoQueSusMiembrosSon(List<String> miembros) {

        this.miembros = miembros;

        var crearGruposButton = driver.findElement(By.id("crearGruposButton"));
        crearGruposButton.click();

        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys("After Office");

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembros.forEach(miembro -> {
            miembrosInput.sendKeys(miembro);
            miembrosInput.sendKeys(Keys.ENTER);
        });

        driver.findElement(By.id("guardarGrupoNuevoButton")).click();

        var wait = new WebDriverWait(driver, 2);
        wait.until(visibilityOfElementLocated(By.id("mensajesToast")));
    }

    @Entonces("visualiza dentro del listado el grupo con los miembros indicados")
    public void visualizaDentroDelListadoElGrupoConLosMiembrosIndicados() {

        var grupoTR = driver.findElements(By.cssSelector("app-grupos table tr"));
        assertThat(grupoTR).hasSizeGreaterThan(1);

        var campoTDs = grupoTR.get(1).findElements(By.tagName("td"));
        assertThat(campoTDs.get(0).getText()).isNotEmpty();
        assertThat(campoTDs.get(3).getText()).contains(this.miembros);
    }

    @Cuando("el usuario intenta crear un grupo indicando un único miembro")
    public void elUsuarioIntentaCrearUnGrupoIndicandoUnUnicoMiembro() {

        var crearGruposButton = driver.findElement(By.id("crearGruposButton"));
        crearGruposButton.click();

        driver.findElement(By.id("nombreGrupoNuevoInput")).sendKeys("Regalo de cumpleaños");

        var miembrosInput = driver.findElement(By.id("miembrosGrupoNuevoInput"));
        miembrosInput.sendKeys("Oscar");
        miembrosInput.sendKeys(Keys.ENTER);
        driver.findElement(By.id("guardarGrupoNuevoButton")).click();

        var wait = new WebDriverWait(driver, 5);
        mensaje = wait.until(visibilityOfElementLocated(By.id("mensajesToast"))).getText();
    }

    @Entonces("no debería crear el grupo con un único miembro")
    public void noDeberiaCrearElGrupoConUnUnicoMiembro() {

        // TODO
    }

    @Y("debería ser informado que necesita tener al menos dos miembros")
    public void deberiaSerInformadoQueNecesitaTenerAlMenosDosMiembros() {

        assertThat(mensaje).contains("Error", "No se puede guardar");
    }
}
