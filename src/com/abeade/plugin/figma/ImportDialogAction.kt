package com.abeade.plugin.figma

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.file.PsiJavaDirectoryImpl

class ImportDialogAction : AnAction() {

    private companion object {

        private const val RES_DIRECTORY = "res"
    }

    override fun update(anActionEvent: AnActionEvent) {
        val project = anActionEvent.project
        val psiElement: PsiElement? = anActionEvent.dataContext.getData(PlatformDataKeys.PSI_ELEMENT)
        anActionEvent.presentation.isEnabledAndVisible = project != null && psiElement != null &&
                (psiElement as? PsiJavaDirectoryImpl)?.isDirectory == true &&
                (psiElement as? PsiJavaDirectoryImpl)?.name == RES_DIRECTORY
    }

    override fun actionPerformed(anActionEvent: AnActionEvent) {
        val dialog = ImportDialogWrapper(PropertiesComponent.getInstance())
        val result = dialog.showAndGet()
        if (result) {
            System.out.println(dialog.file.toString())
            System.out.println(dialog.ldpiPrefix)
            System.out.println(dialog.mdpiPrefix)
            System.out.println(dialog.hdpiPrefix)
            System.out.println(dialog.xhdpiPrefix)
            System.out.println(dialog.xxhdpiPrefix)
            System.out.println(dialog.xxxhdpiPrefix)
        }
    }
}
