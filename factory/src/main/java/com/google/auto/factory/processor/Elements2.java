/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.auto.factory.processor;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.lang.model.element.ElementKind.CLASS;
import static javax.lang.model.element.ElementKind.PACKAGE;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.STATIC;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

import com.google.common.collect.ImmutableSet;

final class Elements2 {
  private Elements2() { }

  static ImmutableSet<ExecutableElement> getConstructors(TypeElement type) {
    checkNotNull(type);
    checkArgument(type.getKind() == CLASS);
    return ImmutableSet.copyOf(ElementFilter.constructorsIn(type.getEnclosedElements()));
  }

  static boolean isValidSupertypeForClass(TypeElement type) {
    if (!type.getKind().equals(CLASS)) {
      return false;
    }
    if (type.getModifiers().contains(FINAL)) {
      return false;
    }
    if (!type.getEnclosingElement().getKind().equals(PACKAGE)
        && !type.getModifiers().contains(STATIC)) {
      return false;
    }
    if (type.getSimpleName().length() == 0) {
      return false;
    }
    return true;
  }
}
